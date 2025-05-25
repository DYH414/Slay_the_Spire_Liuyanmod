package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Beishui extends CustomCard {
    public static final String ID = "Liuyanmod:Beishui";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Beishui_attack.jpg";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 15;
    private static final int BONUS_DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 5;

    public Beishui() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int finalDamage = this.damage;

        // 统计手牌中剩余攻击牌数量
        int remainingAttacks = 0;
        for (AbstractCard c : p.hand.group) {
            if (c != this && c.type == CardType.ATTACK) {
                remainingAttacks++;
            }
        }

        if (remainingAttacks == 0) {
            finalDamage += BONUS_DAMAGE;
        }

        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Beishui();
    }
}
