package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class GejuQiangxi extends CustomCard {
    public static final String ID = "Liuyanmod:GejuQiangxi";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Gejvqiangxi_attack.png";

    private static final int COST = 1;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_DAMAGE = 3;

    public GejuQiangxi() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.UNCOMMON,  // 蓝色品质对应RARE
                CardTarget.ENEMY);

        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 施加割据状态
        addToBot(new ApplyPowerAction(p, p,
            new GejuPower(p, 1), 1));

        // 造成伤害
        addToBot(new DamageAction(m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE); // 14 → 17
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GejuQiangxi();
    }
}
