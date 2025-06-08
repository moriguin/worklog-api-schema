import { setupServer } from "msw/node";
import { getWorkLogAPIMock } from "../api/workLogAPI.msw.js";

export const startMockServer = () => {
  const server = setupServer();
  server.use(...getWorkLogAPIMock());
  server.listen();
  return server;
};
