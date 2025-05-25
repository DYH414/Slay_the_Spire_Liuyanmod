package Liuyanmod.powers;

import Liuyanmod.cards.liuyan.Qilingong;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class QilingongPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:QilingongPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public QilingongPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.priority = 50; // 优先级高一点，确保先削减力量
        String path128 = "Liuyan/img/powers/BaguazhenPowerPower84.jpg";
        String path48 = "Liuyan/img/powers/BaguazhenPowerPower32.jpg";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.POWER) {
            // 每打出1张能力卡，让手里的麒麟弓降1费
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cardID.equals(Qilingong.ID)) {
                    addToBot(new ReduceCostAction(c.uuid, 1));
                }
            }
        } else if (card.type == AbstractCard.CardType.ATTACK) {
            // 打攻击卡，目标失去1点力量
            if (action.target instanceof AbstractMonster) {
                AbstractMonster target = (AbstractMonster) action.target;
                if (target != null && !target.isDeadOrEscaped()) {
                    addToBot(new ApplyPowerAction(target, owner, new StrengthPower(target, -1), -1));
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
