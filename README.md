# LifecyclePermissionBug
Small android project showing lifecycle permission bug on Android 6 (Marshmallow). Does not occur on higher versions.

The bug is that upon granting a permission, the app hits the `onResume` state but calling `lifecycle.currentState` shows `CREATED` instead of `STARTED` or `RESUMED`

Gif of it in action:

![Gif](https://i.imgur.com/SrXf11M.gif)
