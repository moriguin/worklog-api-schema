import axios, { AxiosRequestConfig } from 'axios';

// CSRFトークンを保存するためのストレージキー
const CSRF_TOKEN_KEY = 'x-xsrf-token';

// CSRFトークンを取得する関数
function getStoredCsrfToken(): string | null {
  return localStorage.getItem(CSRF_TOKEN_KEY);
}

// CSRFトークンを保存する関数
function storeCsrfToken(token: string): void {
  localStorage.setItem(CSRF_TOKEN_KEY, token);
}

// カスタムaxiosインスタンスを作成
export const customInstance = axios.create();

// リクエストインターセプター：POST/PATCH/DELETEにCSRFトークンを自動追加
customInstance.interceptors.request.use((config) => {
  const method = config.method?.toLowerCase();
  
  // POST/PATCH/DELETEの場合、CSRFトークンをヘッダーに追加
  if (method && ['post', 'patch', 'delete'].includes(method)) {
    const token = getStoredCsrfToken();
    if (token) {
      config.headers = config.headers || {};
      config.headers['X-XSRF-TOKEN'] = token;
      console.log('CSRF Request:', method, token);
    } else {
      console.log('CSRF Request: No token found for', method);
    }
  }
  
  return config;
}, (error) => {
  return Promise.reject(error);
});

// レスポンスインターセプター：X-XSRF-TOKENを自動保存
customInstance.interceptors.response.use((response) => {
  // レスポンスヘッダーからCSRFトークンを取得
  const csrfToken = response.headers['x-xsrf-token'];
  if (csrfToken) {
    console.log('CSRF Response:', csrfToken);
    storeCsrfToken(csrfToken);
  } else {
    console.log('CSRF Response: No token in response headers');
  }
  
  return response.data;
}, (error) => {
  return Promise.reject(error);
});

// orvalで使用するエクスポート関数
export const customAxiosInstance = <T = any>(config: AxiosRequestConfig): Promise<T> => {
  return customInstance(config);
};

export default customAxiosInstance;