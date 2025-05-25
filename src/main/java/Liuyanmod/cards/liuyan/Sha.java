package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.powers.ShaMarkPower;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Sha extends CustomCard {
    public static final String ID = "Liuyanmod:Sha";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = strings.UPGRADE_DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Sha.png";

    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DAMAGE = 3;

    public Sha() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成基础伤害
        int markAmount = m.hasPower("Liuyanmod:ShaMarkPower") ? m.getPower("Liuyanmod:ShaMarkPower").amount : 0;
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage + markAmount, this.damageTypeForTurn)));
        // 施加 ShaMarkPower
        this.addToBot(new ApplyPowerAction(m, p, new ShaMarkPower(m, 1), 1));

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sha();
    }
}