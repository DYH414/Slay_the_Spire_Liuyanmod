package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Nanmanruqin extends CustomCard {
    public static final String ID = "Liuyanmod:Nanmanruqin";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Nanmanruqin.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 3;

    public Nanmanruqin() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                int actualDamage = this.damage;

                // 如果敌人不是攻击意图
                if (mo.getIntentBaseDmg() < 0) {
                    actualDamage *= 2;
                }

                addToBot(new DamageAction(
                        mo,
                        new DamageInfo(p, actualDamage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HEAVY
                ));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
        }
    }
    public AbstractCard makeCopy() {
        return new Nanmanruqin();
    }
}
