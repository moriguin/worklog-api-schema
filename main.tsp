import "@typespec/http";
import "@typespec/openapi";

using Http;
using OpenAPI;

@service(#{ title: "Work Log API" })
@info(#{ version: "1.0.0" })   
namespace WorkLogService;

@format("ulid")
@minLength(26)
@maxLength(26)
@doc("ULID形式のユニークID")
scalar Id extends string;

@doc("高精度な10進数値を表す文字列")
@format("decimal")
@pattern("^-?\\d+(\\.\\d+)?$")
scalar Decimal extends string;

model WorkLog {
  id: Id;

  @doc("日付 (YYYY-MM-DD 形式)")
  @format("date")
  @example("2025-07-01")
  date: string;

  @doc("当日の合計労働時間 (時間単位)")
  @example("5.5")
  total_h: Decimal;

  @doc("当日のコミュニケーション時間 (時間単位)")
  @example("2.5")
  comm_h: Decimal;

  @doc("当日のコーディングなどの作業時間 (時間単位)")
  @example("3.0")
  work_h: Decimal;

  @doc("メモ")
  @example("")
  @maxLength(500)
  memo?: string;
}

model WorkLogAggregation {
  @doc("その月の合計労働時間 (時間単位)")
  total_h: Decimal;

  @doc("その月の合計コミュニケーション時間 (時間単位)")
  comm_h: Decimal;

  @doc("その月の合計作業時間 (時間単位)")
  work_h: Decimal;

  @doc("日ごとのワークログ一覧")
  logs: WorkLog[];
}

@withoutOmittedProperties("id" | "total_h")
model RequestWorkLog is WorkLog;

@@pattern(RequestWorkLog.work_h, "^([0-7](\\.\\d+)?|8(\\.0+)?)$");
@@pattern(RequestWorkLog.comm_h, "^([0-7](\\.\\d+)?|8(\\.0+)?)$");

@error
model ApiError {
  code: int32;
  message: string;
}

@route("/worklogs")
@tag("WorkLogs")
interface WorkLogApi {
  /** WorkLog の一覧を取得 */
  @get list(
    @doc("YYYY-MM 形式 (例: 2025-07) ")
    @query month: string
  ): WorkLogAggregation;

  /** WorkLog を取得 */
  @get read(@path id: Id): WorkLog;

  /** WorkLog を作成 */
  @post create(@body body: RequestWorkLog): WorkLog;

  /** WorkLog を更新 */
  #suppress "@typespec/http/patch-implicit-optional"
  @patch update(@path id: Id, @body body: RequestWorkLog): WorkLog;

  /** WorkLog を削除 */
  @delete delete(@path id: Id): void | ApiError;
}

model Me {
  @doc("ユーザー名")
  @maxLength(255)
  name: string;

  @doc("プロフィール画像URL")
  @format("uri")
  picture: url;
}

@route("/me")
@tag("User")
interface UserApi {
  /** ログインユーザー情報を取得 */
  @get read(): Me;
}

