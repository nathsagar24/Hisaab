# Hisaab (Android)

A native Android wrapper around the Hisaab page. The page itself lives in
`app/src/main/assets/index.html`; all your data is saved on the phone, inside the app.

## Build the APK (Android Studio)
1. Install Android Studio (free): https://developer.android.com/studio
2. File > Open, and choose this `Hisaab` folder. Let Gradle sync finish (needs internet the first time).
3. Plug in your phone (USB debugging on) and press Run, or use
   Build > Build Bundle(s) / APK(s) > Build APK(s) and copy `app/build/outputs/apk/debug/app-debug.apk`
   to your phone and install it.

## Notes
- Importing PDFs/Excel/ZIP loads small helper libraries from the internet, so those imports need a connection.
- Data is stored inside the app. Uninstalling the app or clearing its storage erases it.
- Data in the web version does not carry over automatically; it lives in a different place.

## Get the APK without Android Studio
Every push to `main` builds the app on GitHub. Open the **Releases** page of this repo on your phone,
download `Hisaab.apk` from the "Hisaab (latest build)" release and install it
(allow "install unknown apps" for your browser when asked).
