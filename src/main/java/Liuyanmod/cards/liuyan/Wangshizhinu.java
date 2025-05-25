package Liuyanmod.cards.liuyan;

import Liuyanmod.characters.MyCharacter;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ALL_ENEMY;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType;

public class Wangshizhinu extends CustomCard {
    public static final String ID = "Liuyanmod:Wangshizhinu";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG_PATH = "Liuyan/img/cards/Wangshizhinu_attack.jpg";

    private static final CardType TYPE = ATTACK;
    private static final CardColor COLOR = MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
    private static final CardRarity RARITY = RARE;
    private static final CardTarget TARGET = ALL_ENEMY;
    private static final int COST = 2;

    private static final int DAMAGE_PER_CARD = 6;
    private static final int UPGRADE_PLUS = 3;

    public Wangshizhinu() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = DAMAGE_PER_CARD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int attackCount = 0;
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == ATTACK) {
                attackCount++;
            }
        }
        int totalDamage = attackCount * this.magicNumber;

        if (totalDamage > 0) {
            for (AbstractMonster mo : com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    this.addToBot(new DamageAction(mo,
                            new DamageInfo(p, totalDamage, DamageType.NORMAL),
                            AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS); // 每张牌的伤害提升
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Wangshizhinu();
    }
}
