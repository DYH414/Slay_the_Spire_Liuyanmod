package Liuyanmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IgnoreBlockDamageAction extends AbstractGameAction {
    private DamageInfo info;

    public IgnoreBlockDamageAction(AbstractMonster target, DamageInfo info) {
        this.target = target;
        this.info = info;
        this.source = info.owner; // 初始化 source
        this.actionType = ActionType.DAMAGE;
    }

    @Override
    public void update() {
        if (this.target != null && this.info != null) {
            // 直接使用 info.output，绕过格挡
            this.target.damage(new DamageInfo(this.source, info.output, DamageInfo.DamageType.HP_LOSS));
            // 确保格挡值被忽略
            this.target.currentBlock = 0;
        }
        this.isDone = true;
    }
}