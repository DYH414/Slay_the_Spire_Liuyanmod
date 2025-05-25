package Liuyanmod.cards.liuyan;

import Liuyanmod.powers.GejuPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Jushi extends CustomCard {
    public static final String ID = "Liuyanmod:Jushi";
    private static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = strings.NAME;
    public static final String DESCRIPTION = strings.DESCRIPTION;
    private static final String IMG = "Liuyan/img/cards/Jushi_attack.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 6; // 基础伤害
    private static final int UPGRADE_DAMAGE = 3; // 升级后的伤害提升

    public Jushi() {
        super(ID, NAME, IMG, COST, DESCRIPTION,
                CardType.ATTACK,
                EXAMPLE_GREEN,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY);

        this.baseDamage = BASE_DAMAGE; // 初始化 baseDamage
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得割据层数
        addToBot(new ApplyPowerAction(p, p, new GejuPower(p, 1), 1));

        // 动态计算实际层数
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (p.hasPower(GejuPower.POWER_ID)) {
                    int stacks = p.getPower(GejuPower.POWER_ID).amount;
                    int damagePerHit = baseDamage; // 使用 baseDamage 作为基础伤害

                    // 多重打击逻辑
                    for (int i = 0; i < stacks; i++) {
                        addToTop(new DamageAllEnemiesAction(p,
                            DamageInfo.createDamageMatrix(damagePerHit, true),
                            damageTypeForTurn,
                            AttackEffect.FIRE));
                    }
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE); // 升级时提升 baseDamage
            this.rawDescription = strings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Jushi();
    }
}
