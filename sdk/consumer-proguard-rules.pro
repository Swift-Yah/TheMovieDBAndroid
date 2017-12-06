-keep class br.com.concrete.themoviebd.sdk.model.** { *; }
-keep class br.com.concrete.themoviebd.sdk.model.*$Companion { *; }

-dontnote android.net.http.*
-dontnote org.apache.http.**

# OkHttp
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Retrofit
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions