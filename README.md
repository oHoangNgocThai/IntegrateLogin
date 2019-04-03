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

*  

# Login app with Twitter

# Login app with Google
