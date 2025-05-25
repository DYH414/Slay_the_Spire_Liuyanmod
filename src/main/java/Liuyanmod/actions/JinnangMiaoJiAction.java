//package Liuyanmod.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.*;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.CardGroup;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.StrengthPower;
//import Liuyanmod.ExampleMod;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class JinnangMiaoJiAction extends AbstractGameAction {
//    private boolean retrieved = false;
//    private CardGroup options = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//
//    public JinnangMiaoJiAction() {
//        this.actionType = ActionType.CARD_MANIPULATION;
//        this.duration = Settings.ACTION_DUR_FAST;
//    }
//
//    @Override
//    public void update() {
//        AbstractPlayer p = AbstractDungeon.player;
//        if (!retrieved) {
//            // 构建选项
//            if (!ExampleMod.usedHeal) {
//                AbstractCard heal = new OptionCard("Heal", "恢复15点生命", null);
//                options.addToTop(heal);
//            }
//            if (!ExampleMod.usedUpgradeAll) {
//                AbstractCard upAll = new OptionCard("UpgradeAll", "升级牌组中所有卡", null);
//                options.addToTop(upAll);
//            }
//            if (!ExampleMod.usedGainStrength) {
//                AbstractCard gainStr = new OptionCard("GainStr", "获得5点力量", null);
//                options.addToTop(gainStr);
//            }
//            if (options.isEmpty()) {
//                isDone = true;
//                return;
//            }
//            // 打开选择界面
//            AbstractDungeon.cardRewardScreen.customCombatOpen(options.group, "锦囊妙计 请选择一个效果", false);
//            retrieved = true;
//        } else if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
//            AbstractCard choice = AbstractDungeon.cardRewardScreen.discoveryCard;
//            switch (choice.cardID) {
//                case "Heal":
//                    AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, 15));
//                    ExampleMod.usedHeal = true;
//                    break;
//                case "UpgradeAll":
//                    AbstractDungeon.actionManager.addToBottom(new UpgradeAllCardsAction());
//                    ExampleMod.usedUpgradeAll = true;
//                    break;
//                case "GainStr":
//                    AbstractDungeon.actionManager.addToBottom(
//                            new ApplyPowerAction(p, p, new StrengthPower(p, 5), 5));
//                    ExampleMod.usedGainStrength = true;
//                    break;
//            }
//            AbstractDungeon.cardRewardScreen.discoveryCard = null;
//            isDone = true;
//        }
//    }
//
//    // 内部简单的占位卡，用于选项展示
//    private static class OptionCard extends AbstractCard {
//        OptionCard(String id, String name, String desc) {
//            super(id, name, null, -2, desc, CardType.SKILL, CardColor.COLORLESS,
//                    CardRarity.SPECIAL, CardTarget.NONE);
//            this.isEthereal = true;
//            this.exhaust = true;
//        }
//        @Override public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, AbstractMonster m) {}
//        @Override public AbstractCard makeCopy() { return new OptionCard(cardID, name, rawDescription); }
//    }
//}
