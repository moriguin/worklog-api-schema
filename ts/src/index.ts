import { startMockServer } from "./mocks/startMockServer.js";
import { getWorkLogAPI } from "./api/workLogAPI.js";

// モックサーバーを起動
startMockServer();

// APIクライアントのインスタンスを取得
const api = getWorkLogAPI();

// WorkLog一覧を取得
async function fetchWorkLogs() {
  try {
    const response = await api.workLogApiList({ month: "2024-03" });
    console.log("WorkLog一覧:", response.data);
  } catch (error) {
    console.error("エラーが発生しました:", error);
  }
}

// 新しいWorkLogを作成
async function createWorkLog() {
  try {
    const newWorkLog = {
      date: "2024-03-22",
      total_h: 8,
      comm_h: 2,
      work_h: 6,
      memo: "新しい作業のメモ",
    };
    const response = await api.workLogApiCreate(newWorkLog);
    console.log("作成されたWorkLog:", response.data);
  } catch (error) {
    console.error("エラーが発生しました:", error);
  }
}

// 特定のWorkLogを取得
async function fetchWorkLog(id: string) {
  try {
    const response = await api.workLogApiRead(Number(id));
    console.log("取得したWorkLog:", response.data);
  } catch (error) {
    console.error("エラーが発生しました:", error);
  }
}

// WorkLogを更新
async function updateWorkLog(id: string) {
  try {
    const updatedWorkLog = {
      date: "2024-03-22",
      total_h: 7,
      comm_h: 1.5,
      work_h: 5.5,
      memo: "更新された作業のメモ",
    };
    const response = await api.workLogApiUpdate(Number(id), updatedWorkLog);
    console.log("更新されたWorkLog:", response.data);
  } catch (error) {
    console.error("エラーが発生しました:", error);
  }
}

// WorkLogを削除
async function deleteWorkLog(id: string) {
  try {
    await api.workLogApiDelete(Number(id));
    console.log("WorkLogを削除しました");
  } catch (error) {
    console.error("エラーが発生しました:", error);
  }
}

// テスト実行
async function runTests() {
  console.log("=== WorkLog一覧の取得 ===");
  await fetchWorkLogs();

  console.log("\n=== 新しいWorkLogの作成 ===");
  await createWorkLog();

  console.log("\n=== 特定のWorkLogの取得 ===");
  await fetchWorkLog("1");

  console.log("\n=== WorkLogの更新 ===");
  await updateWorkLog("1");

  console.log("\n=== WorkLogの削除 ===");
  await deleteWorkLog("1");
}

runTests();
