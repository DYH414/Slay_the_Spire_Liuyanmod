package Liuyanmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RenwangdunPower extends AbstractPower  {
    public static final String POWER_ID = "Liuyanmod:Renwangdun";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RenwangdunPower(AbstractCreature owner, int threshold) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = threshold; // 阈值：多少以下的攻击会被无效化
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        String path128 = "Liuyan/img/powers/RenwangdunPowerPower84.jpg";
        String path48 = "Liuyan/img/powers/RenwangdunPowerPower32.jpg";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL && damageAmount <= this.amount) {
            this.flash();
            return 0;
        }
        return damageAmount;
    }

    @Override
    public void stackPower(int stackAmount) {
        // 仁王盾不允许叠加，保持原有的阈值不变
        // 这里什么都不做，阻止默认的叠加行为
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}