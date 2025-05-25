package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Shandian extends CustomCard {
    public static final String ID = "Liuyanmod:Shandian";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Shandian_skill.png";

    private static final int COST = 3;
    private static final int DAMAGE = 50;
    private static final int HP_LOSS = 10;

    public Shandian() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL, EXAMPLE_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 抽牌前记录手牌数量
        int preDrawSize = p.hand.size();

        // 抽牌动作
        addToBot(new DrawCardAction(p, 1){
            @Override
            public void update() {
                super.update();
                // 在抽牌完成后执行判断
                if (this.isDone) {
                    // 获取最后抽到的那张牌
                    AbstractCard drawn = p.hand.getNCardFromTop(0);
                    if (drawn != null && drawn.type == CardType.SKILL) {
                        addToTop(new DamageAllEnemiesAction(p,
                                DamageInfo.createDamageMatrix(DAMAGE, true),
                                DamageInfo.DamageType.THORNS,
                                AbstractGameAction.AttackEffect.LIGHTNING));
                    } else {
                        addToTop(new LoseHPAction(p, p, HP_LOSS));
                    }
                }
            }
        });
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeBaseCost(2);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shandian();
    }
}
