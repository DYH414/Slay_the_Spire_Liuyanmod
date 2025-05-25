package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Quanmouzhuxin extends CustomCard {
    public static final String ID = "Liuyanmod:Quanmouzhuxin";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "Liuyan/img/cards/Quanmouzhuxin_attack.jpg";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 10;
    private static final int BONUS_DAMAGE = 3;
    private static final int BONUS_DAMAGE_UPGRADED = 5;

    public Quanmouzhuxin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, EXAMPLE_GREEN,
                CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> toDiscard = new ArrayList<>();

        // 收集所有手牌中的攻击牌（不包括自己）
        for (AbstractCard card : p.hand.group) {
            if (card.type == CardType.ATTACK && card != this) {
                toDiscard.add(card);
            }
        }

        // 丢弃这些攻击牌
        for (AbstractCard c : toDiscard) {
            addToBot(new DiscardSpecificCardAction(c, p.hand));
        }

        // 计算总伤害：基础伤害 + 每丢弃一张攻击牌增加的伤害
        int bonusPerCard = this.upgraded ? BONUS_DAMAGE_UPGRADED : BONUS_DAMAGE;
        int totalDamage = this.damage + (toDiscard.size() * bonusPerCard);

        // 造成伤害
        addToBot(new DamageAction(m,
                new DamageInfo(p, totalDamage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Quanmouzhuxin();
    }
}
