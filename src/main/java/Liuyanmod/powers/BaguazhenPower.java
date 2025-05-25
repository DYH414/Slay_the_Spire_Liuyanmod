package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;

public class BaguazhenPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:BaguazhenPower";
    private static final PowerStrings powerStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int turnCounter = 0; // 记录经过的回合数

    public BaguazhenPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1; //
        this.isTurnBased = false;
        String path128 = "Liuyan/img/powers/BaguazhenPowerPower84.jpg";
        String path48 = "Liuyan/img/powers/BaguazhenPowerPower32.jpg";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        turnCounter++;
        if (turnCounter >= 3) {
            turnCounter = 0;
            flash();
            AbstractPlayer p = (AbstractPlayer) owner;
            addToBot(new ApplyPowerAction(p, p, new BufferPower(p, 1), 1));
        }
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
