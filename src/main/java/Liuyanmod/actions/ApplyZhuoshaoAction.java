package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import Liuyanmod.powers.JiedongfengPower;
import Liuyanmod.powers.ZhuoshaoPower;

public class ApplyZhuoshaoAction extends AbstractGameAction {
    private AbstractCreature target;
    private AbstractCreature source;
    private int burnAmount;

    public ApplyZhuoshaoAction(AbstractCreature target, AbstractCreature source, int burnAmount) {
        this.target = target;
        this.source = source;
        this.burnAmount = burnAmount;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        int finalAmount = burnAmount;

        // 检查源是否有借东风能力
        if (source != null && source.hasPower(JiedongfengPower.POWER_ID) && target != source) {
            JiedongfengPower jiedongfengPower = (JiedongfengPower) source.getPower(JiedongfengPower.POWER_ID);
            finalAmount = burnAmount * jiedongfengPower.amount;

            // 触发借东风能力的视觉效果
            jiedongfengPower.flash();
        }

        // 施加灼烧能力
        this.addToBot(new ApplyPowerAction(target, source, new ZhuoshaoPower(target, finalAmount), finalAmount));

        this.isDone = true;
    }
}