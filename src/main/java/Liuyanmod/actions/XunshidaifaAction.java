package Liuyanmod.actions;

import Liuyanmod.powers.XunshidaifaRetainPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class XunshidaifaAction extends AbstractGameAction {
    private AbstractPlayer player;
    private int energyAmount;
    private boolean upgraded;

    public XunshidaifaAction(AbstractPlayer p, int energyAmount, boolean upgraded) {
        this.player = p;
        this.duration = Settings.ACTION_DUR_FAST;
        this.energyAmount = energyAmount;
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && energyAmount > 0) {
            // 1. 抽 X 张牌
            addToTop(new DrawCardAction(energyAmount));

            // 2. 本回合保留 X 张牌（升级后 +1）
            addToTop(new ApplyPowerAction(player, player,
                    new XunshidaifaRetainPower(player, upgraded ? energyAmount + 1 : energyAmount),
                    upgraded ? energyAmount + 1 : energyAmount));


            // 3. 下回合获得 X 点能量
            addToTop(new ApplyPowerAction(player, player,
                    new EnergizedPower(player, energyAmount), energyAmount));

            // 4. 消耗所有能量
            player.energy.use(EnergyPanel.totalCount);
        }

        tickDuration();
    }
}
