package Liuyanmod.relics;

import Liuyanmod.ModHelper;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Tusheyugui extends CustomRelic {

    public static final String ID = ModHelper.makePath("Tusheyugui");
    private static final String IMG = "Liuyan/img/relics/Tusheyugui.png";
    private static final RelicTier TIER = RelicTier.STARTER;
    private static final LandingSound SOUND = LandingSound.FLAT;
    private int triggerCount = 1; // 记录触发次数
    private boolean toggle = false; // 交替音效用

    public Tusheyugui() {
        super(ID, ImageMaster.loadImage(IMG), TIER, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.POWER) {
            return;
        }
        // 延迟到这张卡真正离开手牌后再判断
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                boolean hasAttack = false;
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type == AbstractCard.CardType.ATTACK) {
                        hasAttack = true;
                        break;
                    }
                }

                if (!hasAttack) {
                    int targetCount = getTargetCount(card, m);
                    if (targetCount > 0) {
                        flash();

                        triggerCount++;
                        if (triggerCount % 4 == 0) {
                            String sfx;
                            String message;
                            if (toggle) {
                                sfx = "TUSHEYUGUI_DRAW_1";
                                message = "拒险以图进，备策而施为。";
                            } else {
                                sfx = "TUSHEYUGUI_DRAW_2";
                                message = "夫战者，可施以奇险之策而图常谋!";
                            }
                            CardCrawlGame.sound.play(sfx);
                            toggle = !toggle;

                            // 显示对应的对话框
                            AbstractDungeon.effectList.add(
                                    new com.megacrit.cardcrawl.vfx.ThoughtBubble(
                                            AbstractDungeon.player.dialogX,
                                            AbstractDungeon.player.dialogY,
                                            2.0f,
                                            message,
                                            true
                                    )
                            );
                        }


                        // 抽牌
                        addToTop(new DrawCardAction(targetCount));
                    }
                }

                isDone = true;
            }
        });
    }

    // 获取该卡牌的目标数（包括自己）
    private int getTargetCount(AbstractCard card, AbstractMonster m) {
        int count = 0;

        // 自己是目标
        if (card.target == AbstractCard.CardTarget.SELF ||
                card.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
            count++;
        }

        // 多目标敌人
        if (card.target == AbstractCard.CardTarget.ALL_ENEMY) {
            for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
                if (!mo.isDeadOrEscaped()) {
                    count++;
                }
            }
        } else if (m != null && card.target == AbstractCard.CardTarget.ENEMY) {
            count++; // 单体敌人
        }

        return count;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Tusheyugui();
    }
}
