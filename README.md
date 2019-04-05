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
