package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class NoBlockThisTurnPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:NoBlockThisTurnPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public NoBlockThisTurnPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
        String path128 = "Liuyan/img/powers/NoblockPower84.png";
        String path48 = "Liuyan/img/powers/NoblockPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public float modifyBlock(float blockAmount) {
        // 直接将所有格挡修改为0
        if (blockAmount > 0) {
            flash();
            return 0.0f;
        }
        return blockAmount;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]; // 写一句类似 "本回合无法获得格挡"
    }
}