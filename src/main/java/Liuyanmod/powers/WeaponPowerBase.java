package Liuyanmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class WeaponPowerBase extends AbstractPower {
    public static final String WEAPON_POWER_TYPE = "WEAPON_POWER";

    public WeaponPowerBase(AbstractCreature owner, String powerID) {
        this.owner = owner;
        this.ID = powerID;
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
    }

    // 工具方法：在施加新武器前移除所有旧的武器Power
    public static void removeOtherWeaponPowers(AbstractCreature target) {
        for (AbstractPower p : target.powers) {
            if (p instanceof WeaponPowerBase) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(target, target, p.ID));

            }
        }
    }
}
