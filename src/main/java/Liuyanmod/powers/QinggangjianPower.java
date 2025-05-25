package Liuyanmod.powers;

import Liuyanmod.actions.IgnoreBlockDamageAction;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QinggangjianPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:QinggangjianPower";
    private static final PowerStrings powerStrings =
            com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public QinggangjianPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        String path128 = "Liuyan/img/powers/QinggangjianPowerPower84.png";
        String path48 = "Liuyan/img/powers/QinggangjianPowerPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]; // 描述内容写在 powers.json 中
    }

    // 只在打出攻击牌时触发一次闪光动画
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type== AbstractCard.CardType.ATTACK&&action.target instanceof AbstractMonster)
        {
            this.flash();
            addToTop(new RemoveAllBlockAction((AbstractMonster)action.target,this.owner));
        }
    }
}