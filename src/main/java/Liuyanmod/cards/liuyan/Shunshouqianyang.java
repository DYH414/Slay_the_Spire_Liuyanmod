package Liuyanmod.cards.liuyan;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import static Liuyanmod.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class Shunshouqianyang extends CustomCard {
    public static final String ID = "Liuyanmod:Shunshouqianyang";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "Liuyan/img/cards/Shunshouqianyang_skill.png";
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final int COST = 2;

    public Shunshouqianyang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, EXAMPLE_GREEN, CardRarity.RARE, CardTarget.ENEMY);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && !m.isDeadOrEscaped()) {
            boolean stole = false;

            for (AbstractPower power : m.powers) {
                if (power.type == AbstractPower.PowerType.BUFF) {
                    switch (power.ID) {
                        case "Strength":
                            addToBot(new RemoveSpecificPowerAction(m, p, "Strength"));
                            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        case "Dexterity":
                            addToBot(new RemoveSpecificPowerAction(m, p, "Dexterity"));
                            addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        case "Artifact":
                            addToBot(new RemoveSpecificPowerAction(m, p, "Artifact"));
                            addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        case "Plated Armor":
                            addToBot(new RemoveSpecificPowerAction(m, p, "Plated Armor"));
                            addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        case "Thorns":
                            addToBot(new RemoveSpecificPowerAction(m, p, "Thorns"));
                            addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        case "Metallicize": // 新增金属化
                            addToBot(new RemoveSpecificPowerAction(m, p, "Metallicize"));
                            addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, power.amount), power.amount));
                            stole = true;
                            break;
                        default:
                            continue; // 不是能偷的，继续找
                    }
                    break; // 找到第一个BUFF，不继续找了
                }
            }

            // 如果没偷到，强制移除第一个BUFF
            if (!stole) {
                for (AbstractPower power : m.powers) {
                    if (power.type == AbstractPower.PowerType.BUFF) {
                        addToBot(new RemoveSpecificPowerAction(m, p, power.ID));
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1); // 升级后1费
        }
    }

    @Override
    public CustomCard makeCopy() {
        return new Shunshouqianyang();
    }
}
