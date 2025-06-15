import "@typespec/http";
import "@typespec/openapi";

using Http;
using OpenAPI;

@service(#{ title: "Work Log API" })
@info(#{ version: "1.0.0" })   
namespace WorkLogService;

@format("ulid")
@minLength(1)
@doc("ユニークな ID")
scalar Id extends string;

model WorkLog {
  id: Id;

  @doc("日付 (YYYY-MM-DD 形式)")
  @format("date")
  @example("2025-07-01")
  date: string;

  @doc("当日の合計労働時間 (時間単位)")
  @example(5)
  total_h: int32;

  @doc("当日のコミュニケーション時間 (時間単位)")
  @example(2)
  comm_h: int32;

  @doc("当日のコーディングなどの作業時間 (時間単位)")
  @example(3)
  work_h: int32;

  @doc("メモ")
  @example("")
  @maxLength(500)
  memo?: string;
}

model WorkLogAggregation {
  @doc("その月の合計労働時間 (時間単位)")
  total_h: int32;

  @doc("その月の合計コミュニケーション時間 (時間単位)")
  comm_h: int32;

  @doc("その月の合計作業時間 (時間単位)")
  work_h: int32;

  @doc("日ごとのワークログ一覧")
  logs: WorkLog[];
}

@withoutOmittedProperties("id" | "total_h")
model RequestWorkLog is WorkLog;

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
  ): WorkLogAggregation | ApiError;

  /** WorkLog を取得 */
  @get read(@path id: int32): WorkLog | ApiError;

  /** WorkLog を作成 */
  @post create(@body body: RequestWorkLog): WorkLog | ApiError;

  /** WorkLog を更新 */
  #suppress "@typespec/http/patch-implicit-optional"
  @patch update(@path id: int32, @body body: RequestWorkLog): WorkLog | ApiError;

  /** WorkLog を削除 */
  @delete delete(@path id: int32): void | ApiError;
}

