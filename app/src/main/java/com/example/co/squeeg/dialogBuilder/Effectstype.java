package com.example.co.squeeg.dialogBuilder;


import com.example.co.squeeg.dialogBuilder.effects.BaseEffects;
import com.example.co.squeeg.dialogBuilder.effects.FadeIn;
import com.example.co.squeeg.dialogBuilder.effects.FlipH;
import com.example.co.squeeg.dialogBuilder.effects.FlipV;
import com.example.co.squeeg.dialogBuilder.effects.NewsPaper;
import com.example.co.squeeg.dialogBuilder.effects.SideFall;
import com.example.co.squeeg.dialogBuilder.effects.SlideLeft;
import com.example.co.squeeg.dialogBuilder.effects.SlideRight;
import com.example.co.squeeg.dialogBuilder.effects.SlideTop;
import com.example.co.squeeg.dialogBuilder.effects.Fall;
import com.example.co.squeeg.dialogBuilder.effects.SlideBottom;
import com.example.co.squeeg.dialogBuilder.effects.RotateBottom;
import com.example.co.squeeg.dialogBuilder.effects.RotateLeft;
import com.example.co.squeeg.dialogBuilder.effects.Slit;
import com.example.co.squeeg.dialogBuilder.effects.Shake;


public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    SlideBottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspager(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);
    private Class<? extends BaseEffects> effectsClazz;

    private Effectstype(Class<? extends BaseEffects> mclass) {
        effectsClazz = mclass;
    }

    public BaseEffects getAnimator() {
        BaseEffects bEffects = null;
        try {
            bEffects = effectsClazz.newInstance();
        } catch (ClassCastException e) {
            throw new Error("Can not init animatorClazz instance");
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            throw new Error("Can not init animatorClazz instance");
        }
        return bEffects;
    }
}
