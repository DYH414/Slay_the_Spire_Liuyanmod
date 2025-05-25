package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Chengshengzhuiji extends CustomCard {
    public static final String ID = "Liuyanmod:Chengshengzhuiji";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Chengshengzhuiji_attack.jpg";

    private static final int COST = 0;
    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 3;

    public Chengshengzhuiji() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.COMMON,
                CardTarget.ENEMY);

        this.baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 基础伤害效果保持不变
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        // 修改点：检查割据层数 ≥2
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                GejuPower gejuPower = (GejuPower) p.getPower(GejuPower.POWER_ID);
                if (gejuPower != null && gejuPower.amount >= 2) { // 关键条件
                    AbstractCard newCard = makeStatEquivalentCopy();
                    addToTop(new MakeTempCardInHandAction(newCard, 1));
                }
                isDone = true;
            }
        });
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Chengshengzhuiji();
    }
}
