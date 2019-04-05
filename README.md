# Integrate Login
Integrate Login with Facebook, Twitter, Google

# Login app with Facebook

* Hiện nay rất nhiều các ứng dụng đã tích hợp việc đăng ký tài khoản người dùng thông qua tài khoản Facebook. Bởi bây giờ đa số ai cũng sẽ có cho mình một tài khoản Facebook.
* Việc đăng nhập này dựa trên SDK của Facebook cung cấp cho chúng ta và tích hợp vào ứng dụng của mình.
* Đăng nhập sử dụng Facebook được hướng dẫn chi tiết tại trang [facebook developer](https://developers.facebook.com/docs/facebook-login/android?locale=en)

1. Đầu tiên là bạn phải có một ứng dụng có trên trang **developer facebook**, nếu chưa có thì hãy tạo mới một ứng dụng để tiến hành liên kết. Xem các ứng dụng đã có hoặc là tạo mới ứng dụng tại [đây](https://developers.facebook.com/apps/)
2. Trọn bộ SDK của Facebook cung cấp cho developer ở [Facebook SDK for Android](https://developers.facebook.com/docs/android/componentsdks). Ở đây chúng ta sử dụng **Facebook Login SDK**, để sử dụng cần thêm dependency vào thư mục build.gradle.

* Thêm repository `jcenter()` vào file build.gradle project-level.
* Thêm dependency của facebook login `implementation 'com.facebook.android:facebook-login:[4,5)'` vào file build.gradle app-level.

3. Chỉnh sửa Resource và Manifest

* Thêm **app_id** và **fb_login_protocol_schema** vào file string để sử dụng cho **FacebookActivity**.


```
<string name="facebook_app_id">1274481512658205</string>
<string name="fb_login_protocol_scheme">fb1274481512658205</string>
```

* Thêm quyền kết nối **INTERNET** và thiết đặt của **FacebookActivity** vào trong thư mục **AndroidManifest.xml**.

```
<meta-data android:name="com.facebook.sdk.ApplicationId" 
        android:value="@string/facebook_app_id"/>

<activity android:name="com.facebook.FacebookActivity"
    android:configChanges=
                "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
    android:label="@string/app_name" />

<activity
    android:name="com.facebook.CustomTabActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="@string/fb_login_protocol_scheme" />
    </intent-filter>
</activity>
```

* Thêm package name và default class của ứng dụng của bạn vào phần 5 tại [đây](https://developers.facebook.com/docs/facebook-login/android?locale=en) để Facebook SDK nhận ra ứng dụng của bạn.
* Sau đó thêm **Release Key Hashes** của ứng dụng lên trên ứng dụng mới tạo trên Facebook develop. Để lấy được keystore dev, sử dụng lệnh hoặc hàm để lấy ra:

    * Đối với Linux, sử dụng lệnh dưới đây để lấy ra key dev, những nền tảng khác xem tại [đây](https://medium.com/mindorks/generate-hash-key-for-facebook-and-sha-1-key-for-google-maps-in-android-studio-48d92e4f3c05)
    
    ```
    keytool -exportcert -alias androiddebugkey -keystore debug.keystore | openssl sha1 -binary | openssl base64
    ```
    
    * Có thể sử dụng function để lấy ra được key store debug khi chạy ứng dụng:
    
    ```
    fun getKeyStoreDebug(context: Context) {
        try {
            val info =
                context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")

                md.update(signature.toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e(TAG, "key hash: $something")
            }
        } catch (e1: PackageManager.NameNotFoundException) {
            Log.e(TAG, "name not found: $e1")
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "no such an algorithm: $e")
        } catch (e: Exception) {
            Log.e(TAG, "exception: $e")
        }

    }
    ```

* Bạn có thể sử dụng App event để log lại các event của ứng dụng thông qua Facebook Android SDK. Event này có thể là 1 trong 14 event được xác định trước hoặc có thể tạo thêm những event mới trong ứng dụng của mình để theo dõi.
* Để đăng lý **Logging App Activations** cho ứng dụng của bạn, nó có thể giúp cho bạn thống kê được tần xuất người click, người dùng, ... thông qua Facebook Analytics. Thêm việc đăng ký logging vào  bên trong **onCreate()** của class Application.

```
override fun onCreate() {
        super.onCreate()
        
        // Enable AppEventLogger
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }
    
// For logging
logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"))
```

* Thêm button **Login with Facebook** bên trong ứng dụng từ Facebook SDK. Nó là một thành phần nằm trong **LoginManager**.

```
<com.facebook.login.widget.LoginButton
            android:id="@+id/login_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal"
            android:layout_marginTop="30dp"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
```

* Đăng ký callback để nhận về kết quả khi login thành công hoặc thất bại.


# Login app with Twitter

# Login app with Google

* Hiện tại hầu hết các ứng dụng đều cho phép đăng nhập bằng Google hoặc Facebook, vì rất tiện lợi và không cần người dùng phải nhập thêm bất cứ thông tin gì vì đa số giờ đều có tài khoản Google hoặc Facebook.
* Login theo hướng dẫn của Google đầy đủ ở [đây](https://developers.google.com/identity/sign-in/android/start-integrating)

## Start Integrating
* Đầu tiên, thêm Google's Maven **google()** vào trong repositories và dependency vào trong module app:

```
allprojects {
    repositories {
        google()
        // If you're using a version of Gradle lower than 4.1, you must instead use:
        // maven {
        //     url 'https://maven.google.com'
        // }
    }
}
// App module

dependencies {
    compile 'com.google.android.gms:play-services-auth:16.0.1'
}
```

* Tiếp theo là config Google API Console project để thiết lập các thông tin cần thiết khi liên kết với project trên Google API. Việc này cần cung cấp mã **SHA-1** để định danh ứng dụng của bạn phục vụ cho việc đăng lên chợ ứng dụng sau này.
* Nếu ứng dụng của bạn sử dụng trình xác thực với **backend server**, hoặc truy cập Google API từ **backend server** của bạn, hãy lấy **OAuth 2.0 client ID** ở trong [google console](https://console.developers.google.com/apis/credentials?authuser=4) và tìm type là **Web application**.
> Thêm client id này vào phương thức **requestIdToken**, **requestServerAuthCode** khi bạn tạo đối tượng **GoogleSignInOptions**.

## Add Sign-In

* Tạo đối tượng của **GoogleSignInOptions** để chỉ định cấu hình khi sign-in với Google. Ở đây sử dụng scope **DEFAULT_SIGN_IN**, nếu muốn yêu cầu bổ sung phạm vi để truy cập Google API, hãy chỉ định với [requestScopes()](https://developers.google.com/identity/sign-in/android/additional-scopes?authuser=4).   

```
val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build()
```
* Bạn có thể lấy được các thông tin khác nhau khi sử dụng các phương thức **request...** ví dụ như **requestEmail()**, **requestProfile()**.

* Tạo một đối tượng **GoogleSignInClient** ở activity mà bạn dùng để request sign-in với google account.

```
mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
```

* Kiểm tra xem đã tồn tại user đăng nhập chưa, sử dụng **GoogleSignIn** để lấy ra account cuối cùng sign-in bằng phương thức **getLastSignedInAccount()**.

```
val account = GoogleSignIn.getLastSignedInAccount(this)
```
> Nếu như bạn cần phát hiện ra các thay đổi về trạng thái xác thực của người dùng xảy ra bên ngoài ứng dụng của bạn chẳng hạn như thu hồi mã thông báo truy cập, hãy gọi **GoogleSignInClient.silentSignIn** khi ứng dụng khởi động.

* Sau đó, tạo button sign-in google trong file xml. Có thể thay đổi size của button bằng hàm **setSize()** với tham số tùy chọn, ví dụ như **SignInButton.SIZE_STANDARD**.

```
<com.google.android.gms.common.SignInButton
    android:id="@+id/sign_in_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

* Lấy ra intent để có thể gọi đến giao diện chọn các tài khoản có trong máy bằng **SignInIntent**.

```
private fun signIn(){
    val intent = mGoogleSignInClient.signInIntent
    startActivityForResult(intent, REQUEST_CODE_SIGN_IN)
}
```

* Kết quả sẽ trả về bên trong phương thức **onActivityResult()**, có thể lấy được account tại đây hoặc là nhận được lỗi khi đăng nhập với google account.

```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_CODE_SIGN_IN) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        handleResult(task)
    }
}

private fun handleResult(completeTask: Task<GoogleSignInAccount>) {
    try {
        val account = completeTask.getResult(ApiException::class.java)
        updateUi(account)
    } catch (ex: ApiException) {
        ex.printStackTrace()
        updateUi(null)
        Log.d(TAG, "Sign-In failed with code: ${ex.statusCode}")
    }
}
```

* Để Sign-Out khỏi tài khoản đang đăng nhập, sử dụng phương thức **signOut()** của GoogleSignInClient.

```
private fun signOut() {
    mGoogleSignInClient.signOut()
        .addOnCompleteListener(this) {
        Log.d(TAG, "SignOut: ${it.result}")
        if (it.isSuccessful) {
            Toast.makeText(applicationContext, "Sign out success", Toast.LENGTH_SHORT).show()
        }
    }
}
```

* Bạn cũng nên cung cấp cho người dùng đã đăng nhập bằng Google account cách ngắt kết nối tài khoản của họ. Nếu người dùng xóa tài khoản khỏi ứng dụng của bạn, bạn phải xóa các thông tin liên quan đến người dùng. Sử dụng phương thức **revokeAccess()**.

```
private fun disconnectAccount() {
    mGoogleSignInClient.revokeAccess()
        .addOnCompleteListener(this) {
        Log.d(TAG, "DisconnectAccount: ${it.result}")
        if (it.isSuccessful) {
            Toast.makeText(applicationContext, "Disconnect account success", Toast.LENGTH_SHORT).show()
        }
    }
}
```

## Sign-In with Id token to FireBase Authentication

* Để khi đăng nhập bằng google sign-in trả về được token của người dùng, sử dụng webIdToken khi tạo project trên **Google API Console** rồi sử dụng phương thức **requestIdToken()**.

```
val webClientId = "779671502565-q27gv7hs2boc6l1vtfecicn7al8su8e5.apps.googleusercontent.com"

 val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .requestIdToken(webClientId)
                .build()
```

* Tiếp theo, muốn sử dụng FirebaseAuth để lưu trữ người dùng đăng nhập vào cơ sở dữ liệu của Firebase, hãy tạo một project firebase tương ứng với project của Google API.
* Thêm phương thức đăng nhập vào trong FireBase Authentication, ở đây cần enable sign-in qua Google. 
* Sau khi nhận được kết quả khi sign-in bằng Google account, sử dụng **GoogleSignInAccount** để lấy ra **idToken** để thực hiện việc lưu account lên FireBase Authentication.

```
private fun signInWithCredential(account: GoogleSignInAccount) {
    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d(TAG, "signInWithCredential:success")
                val user = mAuth.currentUser
                Log.d(TAG, "Firebase: CurrentUser:$user")
            } else {
                Log.d(TAG, "signInWithCredential:failure", it.exception)
                Toast.makeText(applicationContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
```

> Để thực hiện login qua FireBase, thêm file cấu hình **google-service.json** vào trong thư mục app. Trước đó việc tạo ra project Google API cũng sinh ra file cấu hình **credentials.json** cũng được để ở đây.

## [Authenticate with Backend Server](https://developers.google.com/identity/sign-in/android/backend-auth?authuser=4)

> Thông thường chúng ta sẽ có một server backend riêng để quản lý việc người dùng nào đang đăng nhập trên server. Để làm như vậy sau khi đăng nhập xong, hãy gửi **idToken** của người dùng đến server để quản lý.

* Sử dụng HTTPS với phương thức POST để gửi idToken của người dùng lên server, ví dụ như:
* Để xác minh tính toàn vẹn của **idToken**, có thể sử dụng mã khóa công khai của Google để xác định tính toàn vẹn, đọc thêm tại [đây](https://developers.google.com/identity/sign-in/android/backend-auth?authuser=4)
* Sử dụng thư viện Google API Client để có thể validate **idToken**, sử dụng đối tượng **GoogleIdTokenVerifier** để verifier token nhận từ server ở lần sau.

```
GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
    // Specify the CLIENT_ID of the app that accesses the backend:
    .setAudience(Collections.singletonList(CLIENT_ID))
    // Or, if multiple clients access the backend:
    //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
    .build();

// (Receive idTokenString by HTTPS POST)

GoogleIdToken idToken = verifier.verify(idTokenString);
```

* Để xác thực **idToken** cũng có thể sử dụng request đến một api dạng như sau:

```
https://oauth2.googleapis.com/tokeninfo?id_token=XYZ123
```

## [Enabling Server-Side Access](https://developers.google.com/identity/sign-in/android/offline-access?authuser=4)

> Việc thêm Sign-In của Google ở trên chỉ thực hiện bên trên client, nếu server muốn thay mặt client gọi ra một API Google thì sẽ cần server phải request access.

* Khi thực hiện tạo object của **GoogleSignInOptions**, sử dụng phương thức **requestServerAuthCode** khi thực hiện **requestScopes()**.

```
GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        ...
        .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
        .build()
```

* Sau khi login thành công, sẽ nhận về được **serverAuthCode()** và gửi code này lên trên server backend để quản lý.

```
val authCode = account.getServerAuthCode()
```

* Trên server sử dụng authCode của client gửi lên để thực hiện **refreshToken()**, từ đó lấy được token mới và thực hiện thay client các dịch vụ mà Google API cung cấp.

```
// Exchange auth code for access token
GoogleClientSecrets clientSecrets =
    GoogleClientSecrets.load(
        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
GoogleTokenResponse tokenResponse =
          new GoogleAuthorizationCodeTokenRequest(
              new NetHttpTransport(),
              JacksonFactory.getDefaultInstance(),
              "https://www.googleapis.com/oauth2/v4/token",
              clientSecrets.getDetails().getClientId(),
              clientSecrets.getDetails().getClientSecret(),
              authCode,
              REDIRECT_URI)  // Specify the same redirect URI that you use with your web
                             // app. If you don't have a web version of your app, you can
                             // specify an empty string.
              .execute();

String accessToken = tokenResponse.getAccessToken();
```
