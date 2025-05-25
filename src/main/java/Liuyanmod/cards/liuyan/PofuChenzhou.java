package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class PofuChenzhou extends CustomCard {
    public static final String ID = "Liuyanmod:PofuChenzhou";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Pofuchenzhou_attack.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 9;
    private static final int DAMAGE_PER_GEJU = 5;
    private static final int UPGRADE_BASE_DAMAGE = 11;
    private static final int UPGRADE_DAMAGE_PER_GEJU = 7;

    public PofuChenzhou() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.COMMON,
                CardTarget.ENEMY);

        this.baseMagicNumber =  this.magicNumber = 5;
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 计算额外伤害
        int gejuDamage = 0;
        if (p.hasPower(GejuPower.POWER_ID)) {
            int gejuAmount = p.getPower(GejuPower.POWER_ID).amount;
            gejuDamage = gejuAmount * (upgraded ? UPGRADE_DAMAGE_PER_GEJU : DAMAGE_PER_GEJU);
        }

        // 总伤害 = 基础伤害 + 割据层数 × 额外伤害系数
        int finalDamage = this.damage + gejuDamage;

        // 造成伤害
        addToBot(new DamageAction(m, new DamageInfo(p, finalDamage, DamageInfo.DamageType.NORMAL),
                com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(2);
            upgradeDamage(UPGRADE_BASE_DAMAGE - BASE_DAMAGE);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PofuChenzhou();
    }
}
