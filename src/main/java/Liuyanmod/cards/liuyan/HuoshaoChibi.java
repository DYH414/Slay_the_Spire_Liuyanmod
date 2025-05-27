package Liuyanmod.cards.liuyan;

import Liuyanmod.actions.ApplyZhuoshaoAction;
import Liuyanmod.powers.ZhuoshaoPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class HuoshaoChibi extends CustomCard {
    public static final String ID = "Liuyanmod:HuoshaoChibi";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Huoshaochibi_attack.png"; // 需创建对应图片

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DAMAGE = 2;
    private static final int BURNING = 4;
    private static final int UPGRADE_PLUS_BURNING = 2;

    public HuoshaoChibi() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, EXAMPLE_GREEN, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = BURNING;
        this.baseMagicNumber = BURNING;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        for (AbstractMonster mo : com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                this.addToBot(new ApplyZhuoshaoAction(m, p, this.magicNumber));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeMagicNumber(UPGRADE_PLUS_BURNING);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HuoshaoChibi();
    }
}