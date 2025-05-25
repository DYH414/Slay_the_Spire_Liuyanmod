package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Baozheng extends CustomCard {
    public static final String ID = "Liuyanmod:TusheBaozheng";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/TusheBaozheng.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 20;
    private static final int UPGRADE_DAMAGE = 5;

    public Baozheng() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int attackCount = 0;
        for (int i = 0; i < p.hand.size(); i++) {
            AbstractCard card = p.hand.getNCardFromTop(i);
            if (card.type == CardType.ATTACK) {
                attackCount++;
            }
        }

        int realDamage = this.damage - (2 * attackCount)+2;
        if (realDamage < 0) realDamage = 0;

        this.addToBot(new DamageAction(
                m,
                new DamageInfo(p, realDamage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY
        ));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE);
        }
    }
    public AbstractCard makeCopy() {
        return new Baozheng();
    }
}
