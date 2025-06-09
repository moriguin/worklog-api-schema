import { setupWorker } from "msw/browser";
import {
  getWorkLogApiListMockHandler,
  getWorkLogApiCreateMockHandler,
  getWorkLogApiReadMockHandler,
  getWorkLogApiUpdateMockHandler,
  getWorkLogApiDeleteMockHandler,
} from "../api/workLogAPI.msw";

export const worker = setupWorker(
  getWorkLogApiListMockHandler(),
  getWorkLogApiCreateMockHandler(),
  getWorkLogApiReadMockHandler(),
  getWorkLogApiUpdateMockHandler(),
  getWorkLogApiDeleteMockHandler()
);
