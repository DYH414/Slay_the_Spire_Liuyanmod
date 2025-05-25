package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Liuyanmod.powers.NoBlockThisTurnPower; // 自定义一个禁止格挡的Power

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Dandaofuhui extends CustomCard {
    public static final String ID = "Liuyanmod:Dandaofuhui";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG = "Liuyan/img/cards/Dandaofuhui_attack.jpg";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 24;
    private static final int UPGRADE_PLUS_DAMAGE = 6;

    public Dandaofuhui() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.isMultiDamage = true; // 多目标伤害
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 群体攻击
        CardCrawlGame.sound.play("Dandaofuhui",true);
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
        // 本回合禁止获得格挡
        addToBot(new ApplyPowerAction(p, p, new NoBlockThisTurnPower(p), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Dandaofuhui();
    }
}
