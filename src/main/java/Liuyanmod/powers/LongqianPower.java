package Liuyanmod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class LongqianPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:LongqianPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean playedAttack = false;
    private boolean playedSkill = false;
    private final boolean upgraded;

    public LongqianPower(AbstractCreature owner, boolean upgraded) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.upgraded = upgraded;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        this.priority = 99;
        String path128 = "Liuyan/img/powers/LongqianPowerPower84.png";
        String path48 = "Liuyan/img/powers/LongqianPowerPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        int extra = owner.hasPower("Liuyanmod:LimuPower") ? 1 : 0;
        CardCrawlGame.sound.play("LONGXING"); // 可调节音量，0.9f 是常用默认
        if (!playedAttack) {
            addToBot(new GainEnergyAction(1 + extra));
        }
        if (!playedSkill) {
            addToBot(new DrawCardAction(1 + extra));
        }

        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            playedAttack = true;
        }
        if (card.type == AbstractCard.CardType.SKILL) {
            playedSkill = true;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
