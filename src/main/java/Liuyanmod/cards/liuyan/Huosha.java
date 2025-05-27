package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.ZhuoshaoPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;


public class Huosha extends CustomCard {
    public static final String ID = "Liuyanmod:Huosha";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "Liuyan/img/cards/Huosha_attack.png";

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int MAGIC = 3; // 灼烧层数
    private static final int UPGRADE_PLUS_MAGIC = 1;

    public Huosha() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m, p,
                new ZhuoshaoPower(m, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Huosha();
    }
}
