# Socks7 / V2rei — Android Client

Dedicated Android client for Socks7 / V2rei proxy.

## Features

- Save server, port, username, password
- One-tap copy SOCKS5 link
- Share config
- Dark / neon UI (Socks7 branding)

## Build APK (Android Studio)

1. Install [Android Studio](https://developer.android.com/studio)
2. Open this folder as a project
3. Wait for Gradle sync
4. **Build → Build Bundle(s) / APK(s) → Build APK(s)**
5. Install `app/build/outputs/apk/debug/app-debug.apk` on your phone

## Build from command line

```bash
# needs Android SDK + JDK 17
./gradlew assembleDebug
```

APK path:
```
app/build/outputs/apk/debug/app-debug.apk
```

## Usage

1. Open the app
2. Enter your server IP, port `7777`, username & password
3. Tap **Save Config**
4. Tap **Copy SOCKS5 Link**
5. Paste into V2Box / NekoBox / any SOCKS5 app

> Full system VPN (Tun2Socks) can be added in a future version.

## Server

```
Type : SOCKS5
Port : 7777
```

Dual branding: **Socks7** = **V2rei**
