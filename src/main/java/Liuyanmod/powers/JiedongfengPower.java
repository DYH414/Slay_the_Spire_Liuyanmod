package Liuyanmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class JiedongfengPower extends AbstractPower {
    public static final String POWER_ID = "Liuyanmod:JiedongfengPower";
    private static final PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public JiedongfengPower(AbstractCreature owner, int multiplier) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = multiplier;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.loadRegion("storm");
        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 2) {
            this.description = DESCRIPTIONS[0]; // "每次给予敌人的灼烧标记翻倍。"
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]; // "每次给予敌人的灼烧标记变为 X 倍。"
        }
    }

    // 当施加能力时触发，用于修改灼烧层数
    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(ZhuoshaoPower.POWER_ID) && source == this.owner && target != this.owner) {
            // 闪烁效果提示能力触发
            this.flash();
        }
    }

    // 修改施加能力的数量
    public int modifyBurnAmount(int originalAmount) {
        return originalAmount * this.amount;
    }
}