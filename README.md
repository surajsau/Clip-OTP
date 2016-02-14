# Easy OTP [<img alt="Get it on Google Play" height="60px" src="https://play.google.com/intl/en_us/badges/images/apps/en-play-badge-border.png" />][1]

Copy. Paste. Sit back and relax.
<p align="center">
    <img src="https://cloud.githubusercontent.com/assets/5493586/13035295/4efbbf50-d372-11e5-8fe0-6462728f1346.png?raw=true" width="312" height="312" />
    <img src="https://cloud.githubusercontent.com/assets/5493586/13035298/64ea8580-d372-11e5-908b-f2f8b2c71bf1.png?raw=true" width="312" height="312" />
</p>

With Easy OTP, you can copy the OTP from your SMS without the need of opening the SMS inbox app. Not kidding!

Features:
 * Easy OTP doesn't require internet to run
 * It's super secure. Yes, I'm not going to steal any data from your phone or from your SMS. Trust me!
 * You always have the flexibility to turn our service on/off in the settings screen

Stuffs that I learnt
--------------------

 * [BroadcastReceiver](http://developer.android.com/reference/android/content/BroadcastReceiver.html) for receiving incoming SMS and Clipboard
 * [Service](http://developer.android.com/guide/components/services.html) for the [Heads-up](http://developer.android.com/guide/topics/ui/notifiers/notifications.html#Heads-up) notification
 * [/Reg(exp){2}lained/](https://www.youtube.com/watch?v=EkluES9Rvak) awesome video for understanding regex

Other libraries used:
---------------------
[AppIntro by PaoloRotolo](https://github.com/PaoloRotolo/AppIntro) used for App tour and instructions.

[1]: https://play.google.com/store/apps/details?id=com.halfplatepoha.otp
