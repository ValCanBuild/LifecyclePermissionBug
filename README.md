# LifecyclePermissionBug
Small android project showing lifecycle permission bug

The bug is that upon granting a permission, the app hits the `onResume` state but calling `lifecycle.currentState` shows `CREATED` instead of `STARTED` or `RESUMED`
