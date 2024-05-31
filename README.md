## **專案介紹**

**SuperDuperDrive Cloud Storage**

這是一家正在進軍雲端儲存市場的全新公司，目前已經面臨來自 Google Drive 和 Dropbox 等競爭對手的激烈競爭。

然而，這並沒有影響他們的士氣。他們希望在其應用程式中包含個人資訊管理功能，以在競爭中脫穎而出，而最小可行產品包括以下三個功能：

1. 檔案管理: 可以上傳、下載、刪除檔案
2. 筆記管理: 能夠新增、編輯、刪除筆記
3. 密碼備忘錄: 儲存管理其他網站的帳號密碼

## Starter Code

Mentor 事先幫同學準備好一些半成品，包含 HTML template、加解密服務、雜湊服務、Selenium 測試範例。  
同學們將在這個課程作業裡，使用 Spring Boot 實作資料存儲，透過 Thymeleaf 完成 HTML 套版，並啟用 Spring Security 完成基本的用戶身分驗證，以及使用 Selenium 完成 e2e Testing。

可以在 [這裡](https://github.com/udacity/nd035-c1-spring-boot-basics-project-starter/tree/master/starter/cloudstorage) 下載 Starter Code。

## **主要任務**

### 後端

#### 使用 **Spring Security 管理頁面存取**

1. 必須限制未經授權的使用者存取登入和註冊頁面以外的頁面
2. Spring Boot 內建支援處理對 `/login` 和 `/logout` endpoints 的呼叫。必須使用安全性配置，覆蓋預設登入頁面
3. 需要實作一個自訂的 AuthenticationProvider，透過將使用者的憑證與資料庫中儲存的憑證進行比對來授權使用者登入

#### 使用 Controller 處理前端呼叫

1. 將應用程式資料和功能綁定到前端
2. 負責確定應用程式向使用者顯示哪些錯誤訊息，以確保流暢的使用者體驗

#### 使用 MyBatis Mappers 呼叫資料庫

1. 可以設計 POJO 類別來匹配資料庫中的資料，其欄位應與需求和資料類型相匹配
2. 將這些模型類別與資料庫的資料連接起來，為每個 POJO Model 實作 MyBatis Mapper Interface
3. 這些 Mappers 具有功能所需的 SQL Statements，以支援 Model 的基本 CRUD 操作

### 前端

#### **Login Page**

<img width="300" src="https://github.com/benny-sun/CloudStorage/assets/22260295/ebb66552-e7f1-4207-9eb8-11c509b4e1f4"/>

1. 允許每個人訪問此頁面，並且使用者可以使用此頁面登入自己的 Cloud Storage
2. 在此頁面上顯示登入錯誤，例如無效的使用者名稱/密碼

#### Signup Page

<img width="400" src="https://github.com/benny-sun/CloudStorage/assets/22260295/5f5e7d57-0427-498d-9180-cbb47988adc5"/>

1. 允許每個人訪問此頁面，並且可以使用此頁面註冊自己的 Cloud Storage 帳戶
2. 需要驗證使用者是否存在，並在出重複註冊時，在頁面上顯示這些錯誤
3. 使用者密碼必須被安全保存! 必須將密碼 Hash 之後，再儲存到資料庫

#### Home Page

1. 有一個登出按鈕，允許使用者登出應用程式並保持其資料的隱私
2. 應用程式的中心，包含三個必要功能: 檔案管理、筆記管理、密碼備忘錄

##### Files (檔案管理)

<img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/29313460-1080-44ad-8aea-a08f9b65f438"/>

1. 使用者應該能夠下載或刪除先前上傳的檔案
2. 顯示與文件操作相關的任何錯誤，例如: 使用者不應該能夠上傳兩個同名的文件

##### Notes (筆記管理)

1. 使用者能夠新增筆記並查看他們新增的筆記列表  
   <img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/a90198a0-2c83-4993-ba3f-1c61848f64d2"/>
2. 使用者能夠編輯或刪除先前建立的筆記  
   <img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/08a31f76-b986-43ae-882b-73bacbbe0652"/>

##### Credentials (密碼備忘錄)

1. 使用者應該能夠儲存其他網站的帳號密碼，並查看他們已儲存的結果
2. 在此列表中顯示密碼時，請確保它們已加密！  
   <img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/3f7bc979-0266-4263-8b27-71e87375c99c"/>
3. 使用者能夠查看/編輯或刪除這些備忘錄。當使用者點擊查看時，他們能夠看到未加密的密碼
   <img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/99cf4ef7-e125-4ecb-86de-675b736a984d"/>

## Testing

調整和修改 Starter Code 裡面的測試程式碼，並確保能夠使用 Selenium 通過所有 e2e 測試。

<img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/696f1d5c-f6d8-4eb8-8b32-3b0ec141923e"/>

## Task Breakdown

<img width="500" src="https://github.com/benny-sun/CloudStorage/assets/22260295/f785f17a-3328-4e9f-82ac-c99565ed35bf"/>
