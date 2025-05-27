// Liuyanmod.powers.NextTurnEntanglePower.java
package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class LimuPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:LimuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public LimuPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        String path128 = "Liuyan/img/powers/LimuPower84.png";
        String path48 = "Liuyan/img/powers/LimuPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        this.addToBot(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(owner, owner, this.ID));
        this.addToBot(new ApplyPowerAction(owner, owner, new EntanglePower(owner)));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]; // 在 power.json 中设置描述
    }
}
