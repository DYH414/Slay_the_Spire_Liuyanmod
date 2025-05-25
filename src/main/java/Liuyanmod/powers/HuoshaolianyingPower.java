package Liuyanmod.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class HuoshaolianyingPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:HuoshaolianyingPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HuoshaolianyingPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        String path128 = "Liuyan/img/testimg/TestPower84.png";
        String path48 = "Liuyan/img/testimg/TestPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onInflictDamage(DamageInfo info, int damageAmount, AbstractCreature target) {
        // 条件：当前灼烧伤害 + 是敌人 + 不是溅射自身
        if (info.owner == this.owner
                && info.type == DamageInfo.DamageType.THORNS
                && target != null
                && target != this.owner
                && damageAmount > 0) {

            flash();

            // 所有敌人（除了已被攻击的那个）都受到同样的灼烧伤害
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped() && mo != target) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(
                            mo,
                            new DamageInfo(this.owner, damageAmount, DamageInfo.DamageType.THORNS),
                            AbstractGameAction.AttackEffect.FIRE
                    ));
                }
            }
        }
    }
}
