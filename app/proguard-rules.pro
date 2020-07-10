# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Prasad\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interfaces
# class:
#-keepclassmembers class fqcn.of.javascript.interfaces.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn rx.**

-dontwarn okio.**

-dontwarn com.squareup.okhttp.*

-dontwarn retrofit.appengine.UrlFetchClient

-keepattributes *Annotation*

-keep class retrofit.** { *; }

-dontwarn retrofit2.Platform$Java8
-dontwarn io.card.**
-dontwarn com.googlecode.mp4parser.**
-dontwarn com.squareup.okhttp.**
-dontwarn com.google.android.gms.ads.**
-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**
-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
-keep class com.stripe.** { *; }
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keep class hdodenhof.**
-dontwarn sun.misc.Unsafe
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-dontwarn javax.annotation.CheckReturnValue
-dontwarn javax.annotation.CheckForNull
-dontwarn javax.annotation.concurrent.GuardedBy
-dontwarn javax.annotation.concurrent.Immutable
-dontwarn javax.annotation.concurrent.ThreadSafe
-dontwarn javax.annotation.concurrent.NotThreadSafe
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keep class android.support.v7.widget.** { *; }