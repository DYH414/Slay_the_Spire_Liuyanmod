package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;




public class QibuchengshiPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:QibuchengshiPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int step = 0;

    public QibuchengshiPower(AbstractPlayer owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        String path128 = "Liuyan/img/powers/QibuchengshiPower84.png";
        String path48 = "Liuyan/img/powers/QibuchengshiPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        switch (step) {
            case 0:
                CardCrawlGame.sound.play("JIUSHI_1");
                this.addToBot(new DrawCardAction(this.owner, 1));
                break;
            case 1:
                CardCrawlGame.sound.play("JIUSHI_2");
                this.addToBot(new GainBlockAction(this.owner, this.owner, 4));
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new JiuPower(this.owner)));
                break;
            case 2:
                CardCrawlGame.sound.play("JIUSHI_3");
                break;
            case 3:
                CardCrawlGame.sound.play("JIUSHI_4");
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, 1)));
                break;
            case 4:
                CardCrawlGame.sound.play("JIUSHI_5");
                this.addToBot(new GainEnergyAction(1));
                break;
            case 5:
                CardCrawlGame.sound.play("JIUSHI_6");
                this.addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(15, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.LIGHTNING));
                break;
            case 6:
                CardCrawlGame.sound.play("JIUSHI_7");
                this.addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 1)));
                break;
        }
        step = (step + 1) ;
        this.amount=step+1;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.step = 0;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        // 高亮提示下一次触发的效果
        String header = "[下一步] " + getStepDescription();
        // 完整流程说明
        String body = DESCRIPTIONS[0];
        this.description = header + " NL " + body;
    }

    private String getStepDescription() {
        switch (step) {
            case 0: return "抽1张牌";
            case 1: return "获得4点格挡";
            case 2: return "下一次攻击造成双倍伤害";
            case 3: return "获得1点敏捷";
            case 4: return "获得1点能量";
            case 5: return "对所有敌人造成15点伤害";
            case 6: return "获得1层无实体";
            default: return "";
        }
    }
}
