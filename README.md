# Worklog API Schema

- このリポジトリは、[TypeSpec](https://typespec.io/) を用いて API スキーマを定義, OpenAPI を生成します。
- [orval](https://orval.dev/) によって TypeScript クライアントコードを生成、gradleによって Kotlin-Spring のコードを生成します。

## 📦 パッケージ更新手順（for maintainers）

このプロジェクトは GitHub Packages（npm）と GitHub Packages（Maven） に公開されるスキーマライブラリです。
@moriguin/worklog-api-schema を更新して別リポジトリから利用するには、以下の手順に従ってください。

```
.
├── main.tsp             # TypeSpec ファイル
├── openapi.yaml         # 自動生成された OpenAPI 仕様
├── build/generated      # gradle によって自動生成された Kotlin クライアントコード
├── ts/                  # orval によって自動生成された TypeScript クライアントコード
├── orval.config.ts      # orval 設定ファイル
├── tspconfig.yaml       # TypeSpec 設定ファイル
└── package.json
```

## 🚀 公開手順

- 必要な変更を加え、orval 等でスキーマを再生成する
- package.json の version を更新（例：0.1.1）
- private: true が入っている場合は 一時的に削除 (packagesに公開できない)

### 🔧 事前準備（初回のみ）

Classic Personal Access Token (PAT) を GitHub で発行する

スコープ：

```
write:packages
read:packages
```

.env ファイルを作成し、以下のように保存：

`GITHUB_PAT=ghp_xxxxxxxx`

.npmrc に以下を追加：

```
@moriguin:registry=https://npm.pkg.github.com
//npm.pkg.github.com/:_authToken=${GITHUB_PAT}
```

以下のコマンドで公開：

```bash
# npm
dotenv -e .env -- npm publish
# Maven
./gradlew publish
```

成功すると、GitHub の Packages タブに反映されます（初回は翌日にやっと表示）。

```bash
dotenv -e .env -- npm view @moriguin/worklog-api-schema --registry=https://npm.pkg.github.com
```

## 💡 Tips

トークンの期限切れに注意（90日なら定期更新）

誰でもインストールできるようにしたい場合は package.json に以下を追加：

```
"publishConfig": {
  "access": "public"
}
```

※memo : npm whoami は .npmrc を直接読むらしい（dotennvしてもダメ）

```
export GITHUB_PAT=ghp_xxxxxxxx
npm whoami --registry=https://npm.pkg.github.com
```

## 📦 GitHub Packages への公開

このパッケージは GitHub Packages に公開され、他のフロントエンドリポジトリから以下のように利用可能です：

```bash
dotenv -e .env -- npm install @moriguin/worklog-api-schema
```

## 🚀 開発フロー（手動での生成）

### スキーマ更新後に必要な手順：

```bash
# TypeSpecコンパイル
npm run tsp:compile
# 全部いり
npm run generate:all
```
