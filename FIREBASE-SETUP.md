# Firebase setup (Stage 2: accounts and shared debts)

1. Go to https://console.firebase.google.com and create a project (free Spark plan is enough).
2. Build > Authentication > Get started > enable **Email/Password**.
3. Authentication > Settings > Authorized domains > add `appassets.androidplatform.net`.
4. Build > Firestore Database > Create database (production mode, any region near you).
5. Firestore > Rules: paste the contents of `firestore.rules` and Publish.
6. Project settings > Your apps > add a **Web app** and copy the `firebaseConfig` object.
7. In `app/src/main/assets/index.html` set `const FB_CONFIG=` to that object, commit and push. The app builds as usual.

Notes
- Users must verify their email before they can see or create shared debts.
- Overdue notices to the other person are in-app and are written when the lender's app opens.
  Push notifications and automatic WhatsApp need Cloud Functions (Blaze plan) and are a later step.
- Phone sign-in is not enabled yet (SMS costs and India DLT registration).
