package Liuyanmod.relics;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class GejuZhiYin extends CustomRelic {
    public static final String ID = "Liuyanmod:GejuZhiYin";
    private static final String IMG = "Liuyan/img/relics/Gejuzhiyin.png"; // 图片路径
    private static final RelicTier TIER = RelicTier.BOSS; // BOSS遗物
    private static final LandingSound SOUND = LandingSound.HEAVY; // 遗物落地音效

    public GejuZhiYin() {
        super(ID, ImageMaster.loadImage(IMG), TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        // 每回合开始时触发
        flash(); // 播放遗物闪光效果
        // 获得 1 层割据
        addToBot(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new GejuPower(AbstractDungeon.player, 1),
                1
        ));
        // 获得 1 点能量
        addToBot(new GainEnergyAction(1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GejuZhiYin();
    }
}
