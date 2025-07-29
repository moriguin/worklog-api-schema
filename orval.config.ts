import { defineConfig } from "orval";

export default defineConfig({
  worklog: {
    input: "./tsp-output/schema/openapi.yaml",
    output: {
      mode: "split",
      target: "./ts/src/api",
      schemas: "./ts/src/api/model",
      client: "axios",
      mock: true,
      override: {
        mutator: {
          path: "./ts/src/api-mutator.ts",
          name: "customAxiosInstance"
        }
      }
    },
  },
});
