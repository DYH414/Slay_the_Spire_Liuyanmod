package Liuyanmod.powers;

import Liuyanmod.cards.liuyan.Sha;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class ZhugeLiannuPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:ZhugeLiannuPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ZhugeLiannuPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.priority = 99;
        String path128 = "Liuyan/img/powers/ZhugeliannuPowerPower84.png";
        String path48 = "Liuyan/img/powers/ZhugeliannuPowerPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        // 只对攻击牌生效，且卡牌 ID 不是“Liuyanmod:Sha”
        if (card.type == AbstractCard.CardType.ATTACK
                && !Liuyanmod.cards.liuyan.Sha.ID.equals(card.cardID)) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInHandAction(new Sha()));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
