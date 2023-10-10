package com.example.artworkapp;

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.LifecycleOwnerKt;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.example.artworkapp.databinding.ActivitySplashScreenBinding;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: SplashScreen.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/example/artworkapp/SplashScreen;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/artworkapp/databinding/ActivitySplashScreenBinding;", "glide", "Lcom/bumptech/glide/RequestManager;", "getGlide", "()Lcom/bumptech/glide/RequestManager;", "setGlide", "(Lcom/bumptech/glide/RequestManager;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "playAnimation", "setupView", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@AndroidEntryPoint
/* loaded from: classes.dex */
public final class SplashScreen extends Hilt_SplashScreen {
    private ActivitySplashScreenBinding binding;
    @Inject
    public RequestManager glide;
    public static final Companion Companion = new Companion(null);
    private static long moveDelay = 2000;
    private static long iconDelay = 1000;

    public final RequestManager getGlide() {
        RequestManager requestManager = this.glide;
        if (requestManager != null) {
            return requestManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("glide");
        return null;
    }

    public final void setGlide(RequestManager requestManager) {
        Intrinsics.checkNotNullParameter(requestManager, "<set-?>");
        this.glide = requestManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivitySplashScreenBinding inflate = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        RequestBuilder<Drawable> load = getGlide().load(Integer.valueOf((int) R.drawable.logo_app));
        ActivitySplashScreenBinding activitySplashScreenBinding = this.binding;
        if (activitySplashScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashScreenBinding = null;
        }
        load.into(activitySplashScreenBinding.imageSplash);
        setupView();
        playAnimation();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new SplashScreen$onCreate$1(this, null), 3, null);
    }

    private final void setupView() {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.hide(WindowInsets.Type.statusBars());
            }
        } else {
            getWindow().setFlags(1024, 1024);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }

    private final void playAnimation() {
        ActivitySplashScreenBinding activitySplashScreenBinding = this.binding;
        if (activitySplashScreenBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySplashScreenBinding = null;
        }
        ObjectAnimator duration = ObjectAnimator.ofFloat(activitySplashScreenBinding.imageSplash, View.ALPHA, 1.0f).setDuration(iconDelay);
        Intrinsics.checkNotNullExpressionValue(duration, "ofFloat(binding.imageSpl…      iconDelay\n        )");
        duration.start();
    }

    /* compiled from: SplashScreen.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/artworkapp/SplashScreen$Companion;", "", "()V", "iconDelay", "", "moveDelay", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}