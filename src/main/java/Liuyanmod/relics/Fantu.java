package Liuyanmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Fantu extends CustomRelic {
    public static final String ID = "Liuyanmod:Fantu";
    private static final Texture IMG = new Texture("Liuyan/img/relics/Fantu.png");
    private static final Texture OUTLINE = new Texture("Liuyan/img/relics/Fantu.png");

    public Fantu() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // 请确保在对应的 localization 文件里写了描述文本
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Fantu();
    }
}
